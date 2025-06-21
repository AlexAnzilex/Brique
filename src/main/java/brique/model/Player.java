package brique.model;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name);
    }

}

