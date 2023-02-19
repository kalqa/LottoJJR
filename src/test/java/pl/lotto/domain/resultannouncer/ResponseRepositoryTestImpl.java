package pl.lotto.domain.resultannouncer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseRepositoryTestImpl implements ResponseRepository {

    private final Map<String, ResultResponse> responseList = new ConcurrentHashMap<>();

    @Override
    public ResultResponse save(ResultResponse resultResponse) {
        return responseList.put(resultResponse.hash(), resultResponse);
    }

    @Override
    public boolean existsById(String hash) {
        return responseList.containsKey(hash);
    }

    @Override
    public Optional<ResultResponse> findById(String hash) {
        return Optional.ofNullable(responseList.get(hash));
    }
}
