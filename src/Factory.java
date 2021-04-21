public class Factory {
    Actor createActor(String actor) throws Exception {
        switch (actor) {
            case "add":
                return new Add();
            case "dec":
                return new Dec();
            case "cdr":
                return null; // TODO
            case "fork":
                return new Fork();
            case "inc":
                return new Inc();
            case "merge":
                return new Merge();
            case "switch":
                return new Switch();
            default:
                try {
                    int c = Integer.parseInt(actor);
                    return new Const(c);
                } catch (NumberFormatException e) {
                }
            throw new Exception("Unknown actor");
        }
    }

    Channel createChannel() {
        return new ArrayChannel();
    }
}
