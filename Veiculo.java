import java.time.LocalDate;

public class Veiculo {
    private String marca;
    private String modelo;
    private String placa;
    private int ano;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Veiculo() {
        this.marca = "";
        this.modelo = "";
        this.placa = "";
        this.ano = 0;
    }

    public Veiculo(String marca, String modelo, String placa, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
    }

    int calculaTempoUso() {
        int anoAtual = LocalDate.now().getYear();
        return this.calculaTempoUso(anoAtual);
    }

    int calculaTempoUso(int anoBase) {
        int tempoUso = anoBase - this.ano;
        return tempoUso;
    }

    Veiculo cloneMe() {
        Veiculo novoVeiculo = this;
        novoVeiculo.marca = this.marca;
        novoVeiculo.modelo = this.modelo;
        novoVeiculo.placa = this.placa;
        novoVeiculo.ano = this.ano;
        return novoVeiculo;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Ano: " + ano + ", Placa: " + placa;
    }
}
