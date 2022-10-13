package fisolution.jsonProject.service;

import fisolution.jsonProject.repository.TargetDataRepository;
import fisolution.jsonProject.repository.TargetResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final TargetDataRepository targetDataRepository;
    private final TargetResultRepository targetResultRepository;



}
