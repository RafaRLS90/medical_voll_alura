package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping //método para cadastrar
    public ResponseEntity cadastrar(@RequestBody DadosCadastroPaciente dados) {

        repository.save(new Paciente(dados));
    }


    @GetMapping //metodo para listar
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);//retornar 200 e  objeto de paginação com os dados do paciente
    }

   @PutMapping //atualização de dados
   @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
       var paciente = repository.getReferenceById(dados.id());
       paciente.atualizarInformacoes(dados);

       return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();//build constroi o objeto ResponseEntity
    }
}
