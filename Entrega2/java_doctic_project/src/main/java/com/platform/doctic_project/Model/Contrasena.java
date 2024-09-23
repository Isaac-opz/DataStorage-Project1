package com.platform.doctic_project.Model;

import com.platform.doctic_project.Model.ENUM.EstadoContrasena;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historial_contrasena")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contrasena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoContrasena estado;

    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    public void setUserId(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUserId'");
    }

    public void setPassword(String encodedPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    }
}
