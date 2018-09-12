import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;


public class POC {

    public static void main(String[] args) {
        Contrato[] contratos = { new Contrato("1", 10, 25.50),
                new Contrato("1", 10, 22.50) };

        Model model = new Model("WarehouseLocation");


        IntVar[] arquivo = model.intVarArray("arquivo", contratos.length, 1, 8, true);
        for(int i = 0; i < contratos.length; i ++){
            for(int j = 0; j < contratos.length; j ++){
                if( i != j && contratos[i].cpf == contratos[j].cpf){
                    if(contratos[i].valor >= contratos[j].valor){
                        model.arithm(arquivo[i], "<", arquivo[j]).post();
                    } else {
                        model.arithm(arquivo[i], ">", arquivo[j]).post();
                    }

                }
            }
        }

        Solver solver = model.getSolver();
        solver.solve();
        System.out.println(arquivo[0]);
        System.out.println(arquivo[1]);

    }
}


class Contrato {

    String cpf;
    int atraso;
    double valor;

    public Contrato(String cpf, int atraso, double valor) {
        this.cpf = cpf;
        this.atraso = atraso;
        this.valor = valor;
    }
}