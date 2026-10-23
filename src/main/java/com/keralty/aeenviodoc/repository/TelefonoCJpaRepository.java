package com.keralty.aeenviodoc.repository;

import com.keralty.aeenviodoc.persistence.DireccionC;
import com.keralty.aeenviodoc.persistence.DirSucursalC;
import com.keralty.aeenviodoc.persistence.TelefonoC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefonoCJpaRepository extends JpaRepository<TelefonoC, Long>, TelefonoCJpaRepositoryCustom {

    List<TelefonoC> findByDirSucursalC(DirSucursalC dirSucursalC);

    List<TelefonoC> findByDireccionC(DireccionC direccionC);
}
