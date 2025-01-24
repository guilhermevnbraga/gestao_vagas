package br.com.guilhermevnbraga.gestao_vagas.modules.company.repositories;

import br.com.guilhermevnbraga.gestao_vagas.modules.company.entities.CompanyEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
