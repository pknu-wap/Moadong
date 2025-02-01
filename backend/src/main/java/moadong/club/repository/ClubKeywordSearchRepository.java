package moadong.club.repository;

import moadong.club.entity.Club;
import moadong.club.payload.dto.ClubSearchResult;
import moadong.club.payload.response.ClubSearchResponse;

import java.util.List;

public interface ClubKeywordSearchRepository {
    List<ClubSearchResult> searchResult(String keyword);
}
