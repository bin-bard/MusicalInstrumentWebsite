
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CheckJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Starting entity validation test...");
            emf = Persistence.createEntityManagerFactory("instrumentPU");
            em = emf.createEntityManager();

            // Start transaction
            em.getTransaction().begin();

            // Test entity creation
            System.out.println("Testing entity creation...");

            // Test if EntityManager is open
            System.out.println("EntityManager is open: " + em.isOpen());

            // Print entity metadata
            System.out.println("Metamodel classes:");
            emf.getMetamodel().getEntities().forEach(type ->
                    System.out.println(type.getName())
            );

            em.getTransaction().commit();
            System.out.println("Test completed successfully!");

        } catch (Exception e) {
            System.err.println("Error during validation:");
            e.printStackTrace();
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}
