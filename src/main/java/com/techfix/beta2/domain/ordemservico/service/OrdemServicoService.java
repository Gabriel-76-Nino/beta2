package com.techfix.beta2.domain.ordemservico.service;

import com.techfix.beta2.domain.ordemservico.OrdemServico;
import com.techfix.beta2.domain.ordemservico.OrdemServicoRepository;
import com.techfix.beta2.domain.ordemservico.StatusOS;
import com.techfix.beta2.domain.ordemservico.dto.DadosAtribuirTecnico;
import com.techfix.beta2.domain.ordemservico.dto.DadosOSCadastro;
import com.techfix.beta2.domain.ordemservico.dto.AtribuirDiagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

//    public List<DadosOSCadastro> converteDtoOS(List<OrdemServico> dto) {
//        return dto.stream().map(
//                (o -> new DadosOSCadastro(o.getCliente(), o.getAparelhoCliente(), o.getProblemaRelatado(),
//                        o.getAcessoriosCliente(), o.getAtendenteRecebeu(), o.getStatusOS()))).toList();
//
//    }
//
//    public OrdemServico atribuirTecnico(DadosAtribuirTecnico dto) {
//        if (dto.tecnicoResponsavel() == null){
//            throw new RuntimeException("não pode ser nulo");
//        }
//        var os = repository.getReferenceById(dto.id());
//        os.setTecnicoResponsavel(dto.tecnicoResponsavel());
//        os.setStatusOS(StatusOS.DIAGNOSTICO);
//        return os;
//    }
//
//    public OrdemServico atribuirDianostico(AtribuirDiagnostico dto) {
//
//        var os = repository.getReferenceById(dto.id());
//        if (dto.diagnosticoTecnico() == null || dto.diagnosticoIgualRelato() == null){
//            throw new RuntimeException("não pode ser vazio");
//        }
//
//        os.setDiagnosticoTecnico(dto.diagnosticoTecnico());
//        os.setDiagnosticoIgualRelato(dto.diagnosticoIgualRelato());
//        return os;
//    }
}
