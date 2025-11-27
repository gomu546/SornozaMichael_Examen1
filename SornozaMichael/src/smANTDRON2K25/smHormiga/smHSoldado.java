package smHormiga;

public class smHSoldado extends smHormiga {

    public void smcomer() {
        if (this.smCarne) {
            System.out.println("Estoy vivo");
        } else {
            System.out.println("Me mataste");
        }
    }
}
