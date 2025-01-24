package br.com.guilhermevnbraga.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guilhermevnbraga.gestao_vagas.exceptions.UserFoundException;
import br.com.guilhermevnbraga.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.guilhermevnbraga.gestao_vagas.modules.candidate.entities.CandidateEntity;

@Service
public class CreateCandidateUseCase {
  @Autowired private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(),
                               candidateEntity.getEmail())
        .ifPresent((user) -> { throw new UserFoundException(); });
    return this.candidateRepository.save(candidateEntity);
  }
}
