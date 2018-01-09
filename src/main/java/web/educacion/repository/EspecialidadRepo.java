/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import web.educacion.model.Especialidad;

/**
 *
 * @author Evelio
 */
public interface EspecialidadRepo extends CrudRepository<Especialidad, Integer>{
    Page<Especialidad> findAll(Pageable pageable);
}
