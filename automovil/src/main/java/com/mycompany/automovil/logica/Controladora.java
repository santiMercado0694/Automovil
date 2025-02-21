

package com.mycompany.automovil.logica;

import com.mycompany.automovil.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void agregarAutomovil(String modelo, String marca, String motor,
            String color, String patente, int cantPuertas) {
        
        Automovil auto = new Automovil();
        auto.setModelo(modelo);
        auto.setMarca(marca);
        auto.setColor(color);
        auto.setMotor(motor);
        auto.setPatente(patente);
        auto.setCantPuertas(cantPuertas);
        
        controlPersis.agregarAutomovil(auto);
    }

    public void borrarAutomovil(int id) {
        controlPersis.borrarAutomovil(id);
    }

    public List<Automovil> traerAutomoviles() {
        return controlPersis.traerAutomoviles();
    }

    public void ModificarAutomovil(Automovil auto, String modelo, String marca,
            String motor, String color, String patente, int cantPuertas) {
        
        auto.setModelo(modelo);
        auto.setMarca(marca);
        auto.setColor(color);
        auto.setMotor(motor);
        auto.setPatente(patente);
        auto.setCantPuertas(cantPuertas);
        
        controlPersis.modificarAutomovil(auto);
        
        
        
    }

    public Automovil traerAutomovil(int id) {
        return controlPersis.traerAutomovil(id);
    }

}
