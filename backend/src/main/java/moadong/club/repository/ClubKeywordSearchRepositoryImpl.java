package moadong.club.repository;

import lombok.AllArgsConstructor;
import moadong.club.payload.dto.ClubSearchResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ClubKeywordSearchRepositoryImpl implements ClubKeywordSearchRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<ClubSearchResult> searchResult(String keyword) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("club_information", "_id", "clubId", "club_info"),
                Aggregation.lookup("club_tags", "_id", "clubId", "club_tags"),
                Aggregation.match(new Criteria().orOperator(
                        Criteria.where("name").regex(keyword, "i"),
                        Criteria.where("club_info.description").regex(keyword, "i"),
                        Criteria.where("club_tags.tag").regex(keyword, "i")
                )),
                Aggregation.unwind("club_tags", true),
                Aggregation.group("_id")
                        .first("name").as("name")
                        .first("state").as("state")
                        .first("classification").as("classification")
                        .first("division").as("division")
                        .first("club_info").as("club_info")
                        .push("club_tags.tag").as("tags"),
                Aggregation.project("name", "state", "classification", "division")
                        .and("club_info.introduction").as("introduction")
                        .and("club_info.logo").as("logo")
                        .and("tags").as("tags")
        );
        AggregationResults<ClubSearchResult> results = mongoTemplate.aggregate(aggregation, "club", ClubSearchResult.class);
        return results.getMappedResults();
    }
}
