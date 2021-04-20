public class Factory {
    Actor createActor(String actor) {

    }

    Channel createChannel() {
        return new ArrayChannel();
    }
}
