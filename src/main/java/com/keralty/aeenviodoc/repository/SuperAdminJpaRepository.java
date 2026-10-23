package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminJpaRepository extends JpaRepository<SuperAdmin, Long>, SuperAdminJpaRepositoryCustom {
}
