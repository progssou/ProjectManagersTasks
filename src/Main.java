import managertasks.task.Task;
import managertasks.task.TaskManager;
import managertasks.task.TaskManagerImpl;
import managertasks.model.Professor;
import managertasks.model.Student;
import managertasks.projets.Project;
import managertasks.task.TaskStates;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TaskManagerImpl taskManager = TaskManagerImpl.getInstance(); // Utilisation du Singleton



        // Création de trois étudiants et deux professeurs
        Student student1 = new Student("Yassine", "etudiant1@example.com", "Licence");
        Student student2 = new Student("Ahmed", "etudiant2@example.com", "Licence");
        Student student3 = new Student("Souhaiel", "etudiant3@example.com", "MPGL");

        Professor professor1 = new Professor("Asma", "professeur1@example.com", "Master");
        Professor professor2 = new Professor("Ahlem", "professeur2@example.com", "Master");

         taskManager.addObserver(professor1); // Ajout du professeur1 comme observateur
         taskManager.addObserver(professor2); // Ajout du professor2 comme observateur

        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit) {
            System.out.println("\nChoisissez un rôle :");
            System.out.println("1. Professeur");
            System.out.println("2. Étudiant");
            System.out.println("3. Quitter");

            int roleChoice = getUserChoice(scanner);

            switch (roleChoice) {
                case 1:
                    professorMenu(taskManager, professor1, scanner);
                    break;
                case 2:
                    studentMenu(taskManager, student1, scanner);
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez Réessayer.");
                    break;
            }
        }
    }

    private static void professorMenu(TaskManager taskManager, Professor professor, Scanner scanner) {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nMenu Professeur :");
            System.out.println("1. Créer un projet et tâche");
            System.out.println("2. Ajouter des tâches à un projet existant");
            System.out.println("3. Assigner des étudiants à une tâche");
            System.out.println("4. Afficher toutes les tâches");
            System.out.println("5. Afficher tous les projets");
            System.out.println("6. Quitter le menu Professeur");

            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    createProjectAndTask(taskManager, scanner);
                    break;
                case 2:
                    addTasksToExistingProject(taskManager, scanner);
                    break;
                case 3:
                    assignStudentsToTask(taskManager, scanner);
                    break;
                case 4:
                    taskManager.displayTasks();
                    break;
                case 5:
                    taskManager.displayAllProjects();
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez Réessayer.");
                    break;
            }
        }
    }

    private static void studentMenu(TaskManager taskManager, Student student, Scanner scanner) {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nMenu Étudiant :");
            System.out.println("1. Consulter tous les projets et tâches");
            System.out.println("2. Modifier l'avancement d'une tâche");
            System.out.println("3. Ajouter un autre étudiant à une tâche");
            System.out.println("4. Quitter le menu Étudiant");

            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    taskManager.displayAllStudents();
                    break;
                case 2:
                    modifyTaskProgress(taskManager, student, scanner);
                    break;
                case 3:
                    addAnotherStudentToTask(taskManager, student, scanner);
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez Réessayer.");
                    break;
            }
        }
    }



    private static void addTasksToExistingProject(TaskManager taskManager, Scanner scanner) {
        System.out.println("Entrez le nom du projet existant :");
        String projectName = scanner.nextLine();

        Project project = taskManager.findProjectByName(projectName);

        if (project != null) {
            // Ajout de tâches au projet existant
            System.out.println("Entrez le nombre de tâches à ajouter :");
            int numberOfTasks = scanner.nextInt();
            scanner.nextLine(); // Consommer le caractère de nouvelle ligne

            for (int i = 0; i < numberOfTasks; i++) {
                addTaskToProject(taskManager, project, scanner);
            }
        } else {
            System.out.println("Projet non trouvé.");
        }
    }



    private static void createProjectAndTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("Entrez le nom du projet : ");
        String projectName = scanner.nextLine();

        // Créer un projet avec le nom spécifié
        Project project = new Project(projectName);
        taskManager.addProject(project);

        System.out.println("Projet créé avec succès.");

        // Ajouter une tâche à ce projet
        addTaskToProject(taskManager, project, scanner);
    }

    private static void addTaskToProject(TaskManager taskManager, Project project, Scanner scanner) {



        System.out.println("Entrez le nom de la tâche :");
        String taskName = scanner.nextLine();

        // Créer une tâche avec le nom spécifié
        Task task = new Task(taskName, Collections.emptyList(), new Date(), new Date()) {
            @Override
            public void performTask() {
                System.out.println("Effectuer une tâche : " + getTaskName());
                setTaskStatus(TaskStates.IN_PROGRESS);
            }
        };

        // Ajouter la tâche au projet spécifié
        taskManager.addTaskToProject(task, project);
        System.out.println("Tâche ajoutée au projet avec succès.");
    }



    private static void assignStudentsToTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("Entrez le nom de la tâche à laquelle assigner des étudiants :");
        String taskName = scanner.nextLine();

        Task task = taskManager.getTasks().stream()
                .filter(t -> t.getTaskName().equals(taskName))
                .findFirst()
                .orElse(null);

        if (task != null) {
            System.out.println("Entrez le nombre d'étudiants à assigner :");
            int numberOfStudents = scanner.nextInt();
            scanner.nextLine(); // Consommer le caractère de nouvelle ligne

            List<Student> assignedStudents = new ArrayList<>();

            for (int i = 0; i < numberOfStudents; i++) {
                System.out.println("Entrez le nom de l'étudiant " + (i + 1) + " :");
                String studentName = scanner.nextLine();
                Student student = new Student(studentName, "", ""); // Création d'un étudiant temporaire
                assignedStudents.add(student);
            }

            task.setAssignedStudents(assignedStudents);
            System.out.println("Étudiants assignés à la tâche avec succès.");
        } else {
            System.out.println("Tâche non trouvée.");
        }
    }

    private static void modifyTaskProgress(TaskManager taskManager, Student student, Scanner scanner) {
        System.out.println("Entrez le nom de la tâche pour laquelle vous souhaitez modifier l'avancement :");
        String taskName = scanner.nextLine();

        Task task = taskManager.getTasks().stream()
                .filter(t -> t.getTaskName().equals(taskName))
                .findFirst()
                .orElse(null);

        if (task != null && task.getAssignedStudents().contains(student)) {
            System.out.println("Entrez le nouvel avancement de la tâche (en pourcentage) :");
            int progress = scanner.nextInt();
            scanner.nextLine(); // Consommer le caractère de nouvelle ligne

          //  task.setTaskProgress(progress); avoir
            System.out.println("Avancement de la tâche modifié avec succès.");
        } else {
            System.out.println("Tâche non trouvée ou vous n'êtes pas assigné à cette tâche.");
        }
    }

    private static void addAnotherStudentToTask(TaskManager taskManager, Student student, Scanner scanner) {
        System.out.println("Entrez le nom de la tâche à laquelle ajouter un autre étudiant :");
        String taskName = scanner.nextLine();

        Task task = taskManager.getTasks().stream()
                .filter(t -> t.getTaskName().equals(taskName))
                .findFirst()
                .orElse(null);

        if (task != null && task.getAssignedStudents().contains(student)) {
            System.out.println("Entrez le nom de l'étudiant à ajouter à la tâche :");
            String newStudentName = scanner.nextLine();
            Student newStudent = new Student(newStudentName, "", ""); // Création d'un nouvel étudiant temporaire

            List<Student> assignedStudents = task.getAssignedStudents();
            assignedStudents.add(newStudent);
            task.setAssignedStudents(Collections.singletonList((Student) assignedStudents));

            System.out.println("Étudiant ajouté à la tâche avec succès.");
        } else {
            System.out.println("Tâche non trouvée ou vous n'êtes pas assigné à cette tâche.");
        }
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }
}
