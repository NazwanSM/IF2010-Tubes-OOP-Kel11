package Enum;

public enum TileState {
    TILLABLE("Tanah bisa dicangkul"),
    TILLED("Tanah sudah dicangkul"),
    PLANTED("Sudah ditanami"),
    WET("Tanah basah");

    private final String description;

    TileState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}