package moadong.club.repository;

import moadong.club.payload.dto.ClubSearchResult;

import java.util.List;

public interface ClubKeywordSearchRepository {
    List<ClubSearchResult> searchResult(String keyword);
}
