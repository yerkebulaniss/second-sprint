package kz.bitlab.academy.secondsprint.service.impl;

import kz.bitlab.academy.secondsprint.entity.ApplicationRequest;
import kz.bitlab.academy.secondsprint.repository.ApplicationRequestRepository;
import kz.bitlab.academy.secondsprint.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestServiceImpl implements ApplicationRequestService {

    private final ApplicationRequestRepository repository;

    @Transactional
    @Override
    public void create(String userName, String courseName, String commentary, String phone) {
        repository.save(new ApplicationRequest(userName, courseName, commentary, phone));
    }

    @Transactional
    @Override
    public void handle(Long id) {
        ApplicationRequest entity = findById(id);
        entity.setHandled(true);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationRequest> findAll() {
        return repository.findAll();
    }

    // TODO: Реализовать
    @Transactional(readOnly = true)
    @Override
    public List<ApplicationRequest> findAllNew() {
        return null;
    }

    // TODO: Реализовать
    @Transactional(readOnly = true)
    @Override
    public List<ApplicationRequest> findAllHandled() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicationRequest findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application request not found"));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Application request not found");

        repository.deleteById(id);
    }

}
