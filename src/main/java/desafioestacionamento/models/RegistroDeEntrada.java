package desafioestacionamento.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
public class RegistroDeEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
private Long duracaoEstadiaMinutos;
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;

    @Column(nullable = false)
    private LocalDateTime dataHoraEntrada;

    public Long getDuracaoEstadiaMinutos() {
        return duracaoEstadiaMinutos;
    }

    public void setDuracaoEstadiaMinutos(Long duracaoEstadiaMinutos) {
        this.duracaoEstadiaMinutos = duracaoEstadiaMinutos;
    }

    private LocalDateTime dataHoraSaida;

    public RegistroDeEntrada() {
    }

    public RegistroDeEntrada(Veiculo veiculo, Estabelecimento estabelecimento) {
        this.veiculo = veiculo;
        this.estabelecimento = estabelecimento;
        this.dataHoraEntrada = LocalDateTime.now(); // Captura o momento atual
    }


    public void registrarSaida() {
        this.dataHoraSaida = LocalDateTime.now();
    }

    public Duration calcularDuracaoEstadia() {
        if (dataHoraEntrada != null && dataHoraSaida != null) {
            return Duration.between(dataHoraEntrada, dataHoraSaida);
        }
        return Duration.ZERO;
    }
    public Long getId() {
        return id;
    }

    public void calcularEAtualizarDuracao() {
        if (dataHoraEntrada != null && dataHoraSaida != null) {
            this.duracaoEstadiaMinutos = Duration.between(dataHoraEntrada, dataHoraSaida).toMinutes();
        } else {
            this.duracaoEstadiaMinutos = null; // Ou trate como achar adequado
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }
}

