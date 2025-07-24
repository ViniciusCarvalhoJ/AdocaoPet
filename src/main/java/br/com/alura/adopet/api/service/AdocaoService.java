package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.MensagemEmailFactory;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private List<ValidacaoSolicitacaoAdocao> validacoes;

    public void solicitarAdocao(SolicitacaoAdocaoDto dto) {

        Pet pet = petRepository.findById(dto.idPet())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));
        Tutor tutor = tutorRepository.findById(dto.IdTutor())
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado"));

        validacoes.forEach(v -> v.validar(dto));

        Adocao adocao = new Adocao();
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adocao.setPet(pet);
        adocao.setTutor(tutor);
        adocao.setMotivo(dto.motivo());
        repository.save(adocao);

        String mensagem = MensagemEmailFactory.gerarMensagemSolicitacaoAdocao(adocao);
        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Solicitação de adoção", mensagem);

    }

    public void aprovar(AprovacaoAdocaoDto dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.setStatus(StatusAdocao.APROVADO);

        String mensagem = MensagemEmailFactory.gerarMensagemAprovacaoAdocao(adocao);
        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Adoção aprovada", mensagem);
    }

    public void reprovar(ReprovacaoAdocaoDto dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.setStatus(StatusAdocao.REPROVADO);
        adocao.setJustificativaStatus(dto.justificativa());

        String mensagem = MensagemEmailFactory.gerarMensagemReprovacaoAdocao(adocao);
        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Adoção reprovada", mensagem);
    }

}
