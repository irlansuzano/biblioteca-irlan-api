package br.edu.infnet.biblioteca_irlan_api.common.messaging;

import br.edu.infnet.biblioteca_irlan_api.common.dto.NotificacaoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.aluguel.name}")
    private String aluguelQueue;

    @Value("${queue.devolucao.name}")
    private String devolucaoQueue;

    public NotificacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarNotificacaoAluguel(NotificacaoDTO notificacao) {
        rabbitTemplate.convertAndSend(aluguelQueue, notificacao);
    }

    public void enviarNotificacaoDevolucao(NotificacaoDTO notificacao) {
        rabbitTemplate.convertAndSend(devolucaoQueue, notificacao);
    }
}
