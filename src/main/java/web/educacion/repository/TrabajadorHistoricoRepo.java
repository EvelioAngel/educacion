/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import web.educacion.model.TrabajadorHistorico;

/**
 *
 * @author Evelio
 */
public interface TrabajadorHistoricoRepo extends PagingAndSortingRepository<TrabajadorHistorico, Integer>{
    
    @Modifying
    @Query(value = "delete from trabajador_historico where id_trabajador = ?1 and extract(year from fecha) = ?2 and extract(month from fecha) = ?3",
           nativeQuery = true)
    void borrarPorIdYFecha(Integer id,int anno, int mes);
}
