package com.ganzi.soccerhub.trip.adaptor.out.persistence;

import com.ganzi.soccerhub.common.PersistenceAdapter;
import com.ganzi.soccerhub.place.adaptor.out.persistence.PlaceMapper;
import com.ganzi.soccerhub.trip.application.command.TravelMatePostSearchCriteria;
import com.ganzi.soccerhub.trip.application.exception.TravelMatePostNotFoundException;
import com.ganzi.soccerhub.trip.application.port.out.AddTravelMatePostPort;
import com.ganzi.soccerhub.trip.application.port.out.LoadTravelMatePostPort;
import com.ganzi.soccerhub.trip.application.port.out.PatchTravelMatePostPort;
import com.ganzi.soccerhub.trip.domain.AgeRange;
import com.ganzi.soccerhub.trip.domain.TravelMatePost;
import com.ganzi.soccerhub.user.domain.Gender;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.ganzi.soccerhub.place.adaptor.out.persistence.QPlaceJpaEntity.placeJpaEntity;
import static com.ganzi.soccerhub.trip.adaptor.out.persistence.QTravelMatePostJpaEntity.travelMatePostJpaEntity;

@PersistenceAdapter
@RequiredArgsConstructor
public class TravelMatePostPersistenceAdaptor implements AddTravelMatePostPort, LoadTravelMatePostPort, PatchTravelMatePostPort {

    private final TravelMatePostRepository travelMatePostRepository;
    private final JPAQueryFactory queryFactory;
    private final TravelMatePostMapper travelMatePostMapper;
    private final PlaceMapper placeMapper;

    @Override
    public TravelMatePost addPost(TravelMatePost travelMatePost) {
        TravelMatePostJpaEntity travelMatePostJpaEntity = travelMatePostMapper.mapToJpaEntity(travelMatePost);
        travelMatePostRepository.save(travelMatePostJpaEntity);

        return travelMatePostMapper.mapToDomainEntity(travelMatePostJpaEntity);
    }

    @Override
    public Optional<TravelMatePost> loadById(TravelMatePost.PostId id) {
        return travelMatePostRepository.findById(id.value()).map(travelMatePostMapper::mapToDomainEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TravelMatePost> search(TravelMatePostSearchCriteria criteria, Pageable pageable) {
        BooleanBuilder whereClause = buildWhereClause(criteria);

        List<TravelMatePostJpaEntity> results = queryFactory
                .selectDistinct(travelMatePostJpaEntity)
                .from(travelMatePostJpaEntity)
                .leftJoin(travelMatePostJpaEntity.places, placeJpaEntity)
                .where(whereClause)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(travelMatePostJpaEntity.id.count())
                .from(travelMatePostJpaEntity)
                .leftJoin(travelMatePostJpaEntity.places, placeJpaEntity)
                .where(whereClause)
                .fetchOne();

        long safeTotalCount = Optional.ofNullable(totalCount).orElse(0L);

        List<TravelMatePost> mappedResults = results.stream()
                .map(travelMatePostMapper::mapToDomainEntity)
                .toList();

        return PageableExecutionUtils.getPage(mappedResults, pageable, () -> safeTotalCount);
    }

    @Override
    public TravelMatePost patch(TravelMatePost travelMatePost) throws TravelMatePostNotFoundException {
        TravelMatePostJpaEntity travelMatePostJpaEntity = travelMatePostRepository.findById(travelMatePost.getId().get().value())
                .orElseThrow(TravelMatePostNotFoundException::new);
        travelMatePostJpaEntity.setTitle(travelMatePost.getTitle());
        travelMatePostJpaEntity.setStartDate(travelMatePost.getStartDate());
        travelMatePostJpaEntity.setEndDate(travelMatePost.getEndDate());
        travelMatePostJpaEntity.removePlaces();
        travelMatePost.getPlaces().forEach(place -> {
            travelMatePostJpaEntity.addPlace(placeMapper.mapToJpaEntity(place));
        });
        travelMatePostJpaEntity.setCapacity(travelMatePost.getCapacity());
        travelMatePostJpaEntity.setGender(travelMatePost.getGender());
        travelMatePostJpaEntity.setAge(travelMatePost.getAge());
        travelMatePostJpaEntity.setDescription(travelMatePost.getDescription());

        return travelMatePostMapper.mapToDomainEntity(travelMatePostJpaEntity);
    }

    private BooleanBuilder buildWhereClause(TravelMatePostSearchCriteria criteria) {
        BooleanBuilder whereClause = new BooleanBuilder();

        if (criteria.getTitle() != null) {
            whereClause.and(travelMatePostJpaEntity.title.containsIgnoreCase(criteria.getTitle()));
        }
        if (criteria.getStartDate() != null) {
            whereClause.and(travelMatePostJpaEntity.startDate.after(criteria.getStartDate()));
        }
        if (criteria.getEndDate() != null) {
            whereClause.and(travelMatePostJpaEntity.endDate.before(criteria.getEndDate()));
        }
        if (criteria.getPlaces() != null && !criteria.getPlaces().isEmpty()) {
            whereClause.and(placeJpaEntity.id.in(criteria.getPlaces()));
        }
        if (criteria.getCapacity() != 0) {
            whereClause.and(travelMatePostJpaEntity.capacity.gt(criteria.getCapacity() - 1));
        }
        if (!criteria.getGender().equals(Gender.ANY)) {
            whereClause.and(travelMatePostJpaEntity.gender.eq(criteria.getGender()));
        }
        if (!criteria.getAge().equals(AgeRange.ANY)) {
            whereClause.and(travelMatePostJpaEntity.age.eq(criteria.getAge()));
        }
        if (criteria.getStatus() != null) {
            whereClause.and(travelMatePostJpaEntity.startDate.eq(criteria.getStartDate()));
        }

        return whereClause;
    }
}
