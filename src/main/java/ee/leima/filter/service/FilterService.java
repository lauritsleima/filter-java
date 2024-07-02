package ee.leima.filter.service;

import ee.leima.filter.dto.CriteriaDto;
import ee.leima.filter.dto.FilterRequest;
import ee.leima.filter.dto.FilterResponse;
import ee.leima.filter.mapper.CriteriaMapper;
import ee.leima.filter.mapper.FilterMapper;
import ee.leima.filter.model.Criteria;
import ee.leima.filter.model.Filter;
import ee.leima.filter.repository.CriteriaRepository;
import ee.leima.filter.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final CriteriaRepository criteriaRepository;
    private final CriteriaMapper criteriaMapper;
    private final FilterRepository filterRepository;
    private final FilterMapper filterMapper;

    public void saveOrUpdateFilter(FilterRequest request) {
        if (request.getFilterId() == null) {
            saveFilter(request);
        } else {
            updateFilter(Long.valueOf(request.getFilterId()), request);
        }
    }

    public void saveFilter(FilterRequest filterRequest) {
        Filter filter = filterMapper.toEntity(filterRequest);
        filter = filterRepository.save(filter);

        for (CriteriaDto criteriaDto : filterRequest.getCriteria()) {
            Criteria criteria = criteriaMapper.toEntity(criteriaDto);
            criteria.setFilterId(filter.getId());
            criteriaRepository.save(criteria);
        }
    }

    public List<FilterResponse> getAll() {
        List<Filter> filters = filterRepository.findAll();
        List<FilterResponse> filterResponses = new ArrayList<>();

        for (Filter filter : filters) {
            List<Criteria> criteria = criteriaRepository.findByFilterId(filter.getId());
            filterResponses.add(filterMapper.toResponse(filter, criteria));
        }

        return filterResponses;
    }

    public void deleteById(Long id) {
        if (filterRepository.existsById(id)) {
            filterRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Filter not found with id: " + id);
        }
    }

    public void updateFilter(Long id, FilterRequest filterRequest) {
        Filter filter = filterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Filter not found with id: " + id));
        filter.setName(filterRequest.getFilterName());
        filterRepository.save(filter);

        updateCriteria(id, filterRequest, filter);
    }

    private void updateCriteria(Long id, FilterRequest filterRequest, Filter filter) {
        List<Criteria> existingCriteria = criteriaRepository.findByFilterId(id);
        List<CriteriaDto> newCriteria = filterRequest.getCriteria();

        for (CriteriaDto criteriaDto : newCriteria) {
            Criteria criteria = existingCriteria.stream()
                    .filter(c -> criteriaDto.getId() != null && c.getId().equals(criteriaDto.getId()))
                    .findFirst()
                    .orElse(null);

            if (criteria == null) {
                // new, add it
                criteria = criteriaMapper.toEntity(criteriaDto);
                criteria.setFilterId(filter.getId());
                criteriaRepository.save(criteria);
            } else {
                // existing, update it
                criteria.setType(criteriaDto.getType());
                criteria.setComparison(criteriaDto.getComparison());
                criteria.setValueText(criteriaDto.getValueText());
                criteria.setValueNumber(criteriaDto.getValueNumber());
                criteria.setValueDate(criteriaDto.getValueDate());
                criteriaRepository.save(criteria);
            }
        }

        for (Criteria criteria : existingCriteria) {
            if (newCriteria.stream().noneMatch(c -> c.getId() != null && c.getId().equals(criteria.getId()))) {
                // no longer exists, delete it
                criteriaRepository.delete(criteria);
            }
        }
    }
}
