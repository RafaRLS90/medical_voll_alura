package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String cpf, Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        //contrutor criado, para criar um objeto do tipo médico
        // em seguida, com this, vai chamar o construtor principal do nosso método
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());

    }
}
