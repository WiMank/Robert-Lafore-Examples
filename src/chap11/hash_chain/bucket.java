package chap11.hash_chain;

class bucket {
    public link[] linkArray = new link[6];
    public static final int MAX_LINKS = 6;
    public int nLinks = 0;

    public bucket() {
    }
}
