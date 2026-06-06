package com.denkitronik.discretasservice;

import com.denkitronik.discretasservice.entities.Modulo;
import com.denkitronik.discretasservice.entities.Tema;
import com.denkitronik.discretasservice.exceptions.RecursoNoEncontradoException;
import com.denkitronik.discretasservice.repositories.IModuloDao;
import com.denkitronik.discretasservice.repositories.ITemaDao;
import com.denkitronik.discretasservice.services.ModuloServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModuloServiceImplTest {

    @Mock private IModuloDao moduloDao;
    @Mock private ITemaDao temaDao;

    @InjectMocks private ModuloServiceImpl moduloService;

    private Modulo modulo1;
    private Tema tema1;

    @BeforeEach
    void setUp() {
        modulo1 = new Modulo();
        modulo1.setId(1L);
        modulo1.setTitulo("Logica y Conjuntos");
        modulo1.setDescripcion("Logica proposicional, predicados e induccion.");
        modulo1.setOrden(1);

        tema1 = new Tema();
        tema1.setId(1L);
        tema1.setTitulo("Logica proposicional");
        tema1.setTexto("Proposiciones, conectores y equivalencias logicas.");
        tema1.setOrden(1);
        tema1.setModulo(modulo1);
    }

    @Test
    void findAll_debeRetornarModulosOrdenados() {
        when(moduloDao.findAllByOrderByOrdenAsc()).thenReturn(List.of(modulo1));

        List<Modulo> resultado = moduloService.findAll();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getTitulo()).isEqualTo("Logica y Conjuntos");
        verify(moduloDao).findAllByOrderByOrdenAsc();
    }

    @Test
    void findById_debeRetornarModulo_cuandoExiste() {
        when(moduloDao.findById(1L)).thenReturn(Optional.of(modulo1));

        Modulo resultado = moduloService.findById(1L);

        assertThat(resultado.getOrden()).isEqualTo(1);
    }

    @Test
    void findById_debeLanzarExcepcion_cuandoNoExiste() {
        when(moduloDao.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> moduloService.findById(99L))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessageContaining("99");
    }

    @Test
    void findTemasByModuloId_debeRetornarTemasDelModulo() {
        when(moduloDao.findById(1L)).thenReturn(Optional.of(modulo1));
        when(temaDao.findByModuloIdOrderByOrdenAsc(1L)).thenReturn(List.of(tema1));

        List<Tema> resultado = moduloService.findTemasByModuloId(1L);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getTexto())
                .isEqualTo("Proposiciones, conectores y equivalencias logicas.");
        verify(temaDao).findByModuloIdOrderByOrdenAsc(1L);
    }

    @Test
    void findTemasByModuloId_debeLanzarExcepcion_cuandoModuloNoExiste() {
        when(moduloDao.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> moduloService.findTemasByModuloId(99L))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void delete_debeEliminarConTemas_cuandoExiste() {
        when(moduloDao.findById(1L)).thenReturn(Optional.of(modulo1));
        doNothing().when(moduloDao).deleteById(1L);

        moduloService.delete(1L);

        verify(moduloDao).deleteById(1L);
    }
}
