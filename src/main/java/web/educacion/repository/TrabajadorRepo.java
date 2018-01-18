/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import web.educacion.model.Trabajador;

/**
 *
 * @author Evelio
 */
public interface TrabajadorRepo extends PagingAndSortingRepository<Trabajador, Integer>{
    Page<Trabajador> findByNombreIgnoreCaseLikeAndCiLikeAndActivo(String nombre, String ci, Boolean activo,Pageable pageable);
    
    Page<Trabajador> findByNombreIgnoreCaseLikeAndCiLikeAndIdEntidadIdMunicipioIdMunicipioAndActivo(String nombre, String ci, Integer idMunicipio, Boolean activo,Pageable pageable);

    String findCiByIdTrabajador(Integer idTrabajador);
}
