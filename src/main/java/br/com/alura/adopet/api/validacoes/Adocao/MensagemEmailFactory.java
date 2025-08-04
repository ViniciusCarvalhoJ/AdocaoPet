package br.com.alura.adopet.api.validacoes.Adocao;

import br.com.alura.adopet.api.model.Adocao;

import java.time.format.DateTimeFormatter;

public class MensagemEmailFactory {

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String gerarMensagemSolicitacaoAdocao(Adocao adocao) {
        return "Olá " + adocao.getPet().getAbrigo().getNome() + "!\n\n" +
                "Uma solicitação de adoção foi registrada hoje para o pet: " + adocao.getPet().getNome() + ".\n" +
                "Favor avaliar para aprovação ou reprovação.";
    }

    public static String gerarMensagemAprovacaoAdocao(Adocao adocao) {
        return "Parabéns " + adocao.getTutor().getNome() + "!\n\n" +
                "Sua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " +
                adocao.getData().format(FORMATADOR) + ", foi aprovada.\n" +
                "Favor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() +
                " para agendar a busca do seu pet.";
    }

    public static String gerarMensagemReprovacaoAdocao(Adocao adocao) {
        return "Olá " + adocao.getTutor().getNome() + "!\n\n" +
                "Infelizmente sua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " +
                adocao.getData().format(FORMATADOR) + ", foi reprovada pelo abrigo " +
                adocao.getPet().getAbrigo().getNome() + " com a seguinte justificativa: " +
                adocao.getJustificativaStatus();
    }
}

