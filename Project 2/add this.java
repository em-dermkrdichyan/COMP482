private static Pair findL1(ArrayList<Integer> X, ArrayList<Integer> Y) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < X.size(); i++) {
            x.add(X.get(i));
            y.add(Y.get(i));
        }
        Collections.sort(x);
        Collections.sort(y);
        return new Pair(x.get(x.size() / 2), y.get(y.size() / 2));
    }
