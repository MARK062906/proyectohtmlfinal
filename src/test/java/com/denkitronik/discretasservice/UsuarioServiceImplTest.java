package com.denkitronik.discretasservice;

import com.denkitronik.discretasservice.entities.Usuario;
import com.denkitronik.discretasservice.exceptions.RecursoNoEncontradoException;
import com.denkitronik.discretasservice.exceptions.RecursoYaExisteException;
import com.denkitronik.discretasservice.repositories.IUsuarioDao;
import com.denkitronik.discretasservice.services.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private IUsuarioDao usuarioDao;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");
        usuario.setApellido("García");
        usuario.setEmail("ana@example.com");
        usuario.setPassword("secreta");
    }

    @Test
    void findById_debeRetornarUsuario_cuandoExiste() {
        when(usuarioDao.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.findById(1L);

        assertThat(resultado.getEmail()).isEqualTo("ana@example.com");
        verify(usuarioDao).findById(1L);
    }

    @Test
    void findById_debeLanzarExcepcion_cuandoNoExiste() {
        when(usuarioDao.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioService.findById(99L))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessageContaining("99");
    }

    @Test
    void save_debeGuardarUsuario_cuandoEmailEsNuevo() {
        when(usuarioDao.existsByEmail("ana@example.com")).thenReturn(false);
        when(usuarioDao.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.save(usuario);

        assertThat(resultado.getNombre()).isEqualTo("Ana");
        verify(usuarioDao).save(usuario);
    }

    @Test
    void save_debeLanzarExcepcion_cuandoEmailYaExiste() {
        when(usuarioDao.existsByEmail("ana@example.com")).thenReturn(true);

        assertThatThrownBy(() -> usuarioService.save(usuario))
                .isInstanceOf(RecursoYaExisteException.class)
                .hasMessageContaining("ana@example.com");

        verify(usuarioDao, never()).save(any());
    }

    @Test
    void delete_debeEliminar_cuandoUsuarioExiste() {
        when(usuarioDao.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioDao).deleteById(1L);

        usuarioService.delete(1L);

        verify(usuarioDao).deleteById(1L);
    }
}
