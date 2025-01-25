package br.com.guilhermevnbraga.gestao_vagas.modules.company.controllers;

import br.com.guilhermevnbraga.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.guilhermevnbraga.gestao_vagas.modules.company.entities.JobEntity;
import br.com.guilhermevnbraga.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {
  @Autowired CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO,
                          HttpServletRequest request) {
    var companyId = request.getAttribute("company_id").toString();
    var jobEntity = JobEntity.builder()
                        .benefits(createJobDTO.getBenefits())
                        .description(createJobDTO.getDescription())
                        .level(createJobDTO.getLevel())
                        .companyId(UUID.fromString(companyId))
                        .build();
    return this.createJobUseCase.execute(jobEntity);
  }
}
