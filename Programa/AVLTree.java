class AVLTree {
  Node root;

  // Función que obtiene la altura de un nodo
  int height(Node N) {
    if (N == null)
      return 0;
    return N.height;
  }

  // Función que devuelve el máximo de dos enteros
  int max(int a, int b) {
    return (a > b) ? a : b;
  }

  // Función que realiza una rotación simple a la derecha
  Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    // Realiza la rotación
    x.right = y;
    y.left = T2;

    // Actualiza las alturas
    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    // Retorna la nueva raíz
    return x;
  }

  // Función que realiza una rotación simple a la izquierda
  Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    // Realiza la rotación
    y.left = x;
    x.right = T2;

    // Actualiza las alturas
    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    // Retorna la nueva raíz
    return y;
  }

  // Obtiene el factor de equilibrio de un nodo
  int getBalance(Node N) {
    if (N == null)
      return 0;
    return height(N.left) - height(N.right);
  }

  // Función para insertar un nodo en el árbol AVL
  Node insert(Node node, int key) {
    /* 1. Realiza la inserción normal de un BST */
    if (node == null)
      return (new Node(key));

    if (key < node.key)
      node.left = insert(node.left, key);
    else if (key > node.key)
      node.right = insert(node.right, key);
    else // Claves duplicadas no son permitidas en un AVL
      return node;
    /* 2. Actualiza la altura de este nodo ancestral */
    node.height = 1 + max(height(node.left), height(node.right));
    /* 3. Obtiene el factor de equilibrio de este nodo ancestral para comprobar
    si se ha desequilibrado */
    int balance = getBalance(node);
    // Si el nodo se ha desequilibrado, entonces hay 4 casos
    // Caso Izquierda-Izquierda
    if (balance > 1 && key < node.left.key)
      return rightRotate(node);
    // Caso Derecha-Derecha
    if (balance < -1 && key > node.right.key)
      return leftRotate(node);
    // Caso Izquierda-Derecha
    if (balance > 1 && key > node.left.key) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }
    // Caso Derecha-Izquierda
    if (balance < -1 && key < node.right.key) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    // Retorna el nodo (sin cambios)
    return node;
  }

  // Función para encontrar el nodo con el valor mínimo
  Node minValueNode(Node node) {
    Node current = node;

    /* Recorre hacia la izquierda para encontrar el nodo hoja más pequeño */
    while (current.left != null)
      current = current.left;

    return current;
  }

  // Función para encontrar el nodo con el valor máximo
  Node maxValueNode(Node node) {
    Node current = node;

    /* Recorre hacia la derecha para encontrar el nodo hoja más grande */
    while (current.right != null)
      current = current.right;

    return current;
  }

    // Función para realizar una búsqueda en el árbol
  Node search(Node root, int key) {
    // Base Cases: root is null or key is present at root
    if (root == null || root.key == key)
      return root;

    // Key is greater than root's key
    if (root.key < key)
      return search(root.right, key);

    // Key is smaller than root's key
    return search(root.left, key);
  }

  // Función para encontrar el nodo padre de un nodo con un valor dado
  Node parent(Node node, int key) {
    if (node == null || node.key == key)
      return null;

        // Caso especial cuando el nodo raíz es el padre
    if ((node.left != null && node.left.key == key) || (node.right != null && node.right.key == key))
      return node;

    // Busca en los subárboles izquierdo y derecho
    Node leftSearch = parent(node.left, key);
    Node rightSearch = parent(node.right, key);

    return (leftSearch != null) ? leftSearch : rightSearch;
  }

    // Función para encontrar el nodo hijo de un nodo con un valor dado
  Node son(Node node, int key) {
    if (node == null)
      return null;

    if (node.left != null && node.left.key == key)
      return node.left;
    else if (node.right != null && node.right.key == key)
      return node.right;

    Node leftSon = son(node.left, key);
    Node rightSon = son(node.right, key);

    return (leftSon != null) ? leftSon : rightSon;
  }

  // Función para realizar la inserción de un nodo con un valor dado
  void insert(int key) {
    root = insert(root, key);
  }

    // Función para obtener el valor mínimo en el árbol
  int getMin() {
    if (root == null)
      return Integer.MIN_VALUE;
    Node minNode = minValueNode(root);
    return minNode.key;
  }

    // Función para obtener el valor máximo en el árbol
  int getMax() {
    if (root == null)
      return Integer.MAX_VALUE;
    Node maxNode = maxValueNode(root);
    return maxNode.key;
  }
}