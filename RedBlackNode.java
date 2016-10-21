class RedBlackNode
 {    
     RedBlackNode left, right;
     int element;
     int color;
     Object value;
 
     /* Constructor */
     public RedBlackNode(int theElement,Object val)
     {
         this(theElement,val, null, null );
     } 
     /* Constructor */
     public RedBlackNode(int theElement,Object val, RedBlackNode lt, RedBlackNode rt)
     {
         left = lt;
         right = rt;
         element = theElement;
         value=val;
         color = 1;
     }    
 }
