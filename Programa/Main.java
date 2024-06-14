public class Main {
  public static void main(String[] args) {
    AVLTree tree = new AVLTree();

    // Inserta las palabras en mayúsculas como sus valores decimales de ASCII
    String inputWord = "JAVA";
    for (char c : inputWord.toCharArray()) {
      tree.insert((int) c);
    }
    
    // Realiza las operaciones de búsqueda, obtener el mínimo y máximo, el padre y el hijo
    int searchKey = (int) 'J';
    Node searchResult = tree.search(tree.root, searchKey);
    System.out.println("Search Result: " + searchResult.key);

    int min = tree.getMin();
    System.out.println("Minimum Value: " + min);

    int max = tree.getMax();
    System.out.println("Maximum Value: " + max);

    int parentKey = (int) 'V';
    Node parentNode = tree.parent(tree.root, parentKey);
    if (parentNode != null)
      System.out.println("Parent of " + parentKey + ": " + parentNode.key);
    else
      System.out.println("Parent of " + parentKey + " not found.");

    int sonKey = (int) 'A';
    Node sonNode = tree.son(tree.root, sonKey);
    if (sonNode != null)
      System.out.println("Son of " + sonKey + ": " + sonNode.key);
    else
      System.out.println("Son of " + sonKey + " not found.");
  }
}