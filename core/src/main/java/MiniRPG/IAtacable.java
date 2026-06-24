package MiniRPG;

public interface IAtacable {
    void attack(IAtacable enemy);
    void beInjured(int quantity);
}
