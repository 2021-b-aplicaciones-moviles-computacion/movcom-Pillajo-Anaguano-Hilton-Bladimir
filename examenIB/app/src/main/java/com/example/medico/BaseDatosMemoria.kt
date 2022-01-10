package com.example.medico

class BaseDatosMemoria {
    companion object {

        val arregloMedico = arrayListOf<BMedico>()
        val arregloPaciente = arrayListOf<BPaciente>()

        init {
            arregloMedico.add(BMedico(1, "Alex", "09867565", 2.3 ))
            arregloMedico.add(BMedico(2, "Marco", "02267689",3.2 ))
            arregloPaciente.add(BPaciente(1, "Nathy", true, 25, 1))
            arregloPaciente.add(BPaciente(2, "Jessica", true, 29, 1))
            arregloPaciente.add(BPaciente(2, "Jessica", true, 29, 2))
        }
    }
}