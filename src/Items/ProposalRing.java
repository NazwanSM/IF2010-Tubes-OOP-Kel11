package items;

public class ProposalRing extends Items {
    public ProposalRing(String name, String type) {
        super(name, type);
        loadImage("/resource/items/proposal_ring/" + name.toLowerCase() + ".png");
    }
}