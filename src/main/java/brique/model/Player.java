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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name);
    }

}

