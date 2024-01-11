import managertasks.task.Task;
import managertasks.task.TaskManager;
import managertasks.task.TaskManagerImpl;
import managertasks.model.Professor;
import managertasks.model.Student;
import managertasks.projets.Project;
import managertasks.task.TaskStates;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        TaskManagerImpl taskManager = TaskManagerImpl.getInstance(); // Utilisation du Singleton
        Scanner scanner = new Scanner(System.in);


         List<Task> tasks = new ArrayList<>();
         List<Project> projects = new ArrayList<>();


        List<Student> students = Arrays.asList(
                new Student("Yassine", "etudiant1@example.com", "Licence"),
                new Student("Iheb", "etudiant2@example.com", "Licence"),
                new Student("Issa", "etudiant2@example.com", "MPGL"),
                new Student("Souhaiel", "etudiant3@example.com", "MPGL")
        );

        List<Professor> professors = Arrays.asList(
                new Professor("Asma", "professeur1@example.com", "Professeur"),
                new Professor("Ahlem", "professeur2@example.com", "Professeur")
        );

       // taskManager.addObserver((Observer) professors); // Ajout du professeur1 comme observateur


        boolean quit = false;

        while (!quit) {
            System.out.println("\nChoisissez un rôle :");
            System.out.println("1. Professeur");
            System.out.println("2. Étudiant");
            System.out.println("3. Quitter");

            int roleChoice = getUserChoice(scanner);

            switch (roleChoice) {
                case 1:
                    professorMenu(taskManager, professors,students,tasks, projects, scanner);
                    break;
                case 2:
                    studentMenu(taskManager, students,tasks, projects, scanner);
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

    private static void professorMenu(TaskManager taskManager, List<Professor> professor,List<Student>  students ,List<Task> tasks, List<Project> projects,Scanner scanner) {
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
                    createProjectAndTask(taskManager,tasks,students,projects, scanner);
                    break;
                case 2:
                    addTasksToExistingProject(taskManager,tasks,students,projects, scanner);
                    break;
                case 3:
                    assignStudentsToTask(taskManager,tasks,students, projects,scanner);
                    break;
                case 4:
                   // displayAllTasks(tasks);
                    getStudentsSelectedProjectAndTask(taskManager,tasks,students, projects,scanner);
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

    private static void studentMenu(TaskManager taskManager,List<Student>  students ,List<Task> tasks, List<Project> projects, Scanner scanner) {
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
                    displayAllTasks(tasks);
                    taskManager.displayAllProjects();
                    break;
                case 2:
                    modifyTaskProgress(taskManager, students,tasks, scanner);
                    break;
                case 3:
                    addAnotherStudentToTask(taskManager, students, scanner);
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









    public static void getStudentsSelectedProjectAndTask(TaskManager taskManager, List<Task> tasks, List<Student> students, List<Project> projects, Scanner scanner) {
         selectProjectAndTask(taskManager,projects,tasks, scanner);

      //  List<Student> selectedStudents = getStudentsForTask(selectedTask, students);
    }



    public static void selectProjectAndTask(TaskManager taskManager,List<Project> projects,List<Task>  tasks, Scanner scanner) {
        System.out.println("Voici la liste des projets déjà créés :");
        taskManager.displayAllProjects();
        // Obtenez le nom du projet choisi par l'utilisateur
        System.out.println("Choisissez un projet existant :");
        String projectName = scanner.next();
        Project selectedProject = null;
        List<Task>  tasksP = null;
        for (Project project : projects) {


            if (project.getProjectName().equals(projectName)){
                System.out.println("Nom: " + project.getProjectName() + ", Description: " + project.getDescription());
                selectedProject = project;
            }
        }
        if(selectedProject!=null){
            Project finalSelectedProject = selectedProject;

            tasksP = tasks.stream().filter(x-> x.getProject().getProjectName().equals(finalSelectedProject.getProjectName()) ).collect(Collectors.toList());
            System.out.println("** Liste des tasks pour le projet  : "+finalSelectedProject.getProjectName());

            for (Task task : tasksP) {

                System.out.println("- Task : "+task.getTaskName());
            }
        }else{
            System.out.println("Projet non existant :");

        }


    }


    public static List<Task> getTasksForProject(Project project, List<Task> tasks) {
        List<Task> projectTasks = new ArrayList<>();
        for (Task task : tasks) {
            System.out.println("project t3addaaaaa  " +project.getProjectName());
            System.out.println("task t3addaaaaa  " +task.getTaskName());
//            if (task.getProject().equals(project)) {
              //  projectTasks.add(task);
                System.out.println( project.getTasks()
                        +" Task of project selected: " + task.getProject().getProjectName());

  //          }
        }
        return projectTasks;
    }


    public static List<Student> getStudentsForTask(Task task, List<Student> students) {
        List<Student> taskStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getTask().equals(task)) {
                taskStudents.add(student);
            }
        }
        return taskStudents;
    }


    private static void displayAllTasks(List<Task> tasks) {
        System.out.println("\nListe de toutes les tâches :");
        for (Task task : tasks) {
            System.out.println(task.getTaskName());
        }
    }



    private static void addTasksToExistingProject(TaskManager taskManager,List<Task> tasks,List<Student> studentss,List<Project> projects,  Scanner scanner) {
        System.out.println("Entrez le nom du projet existant :");
        String projectName = scanner.next();
        Project project = null;
        for (Project pr : projects){
            if (pr.getProjectName().equals(projectName)){
                 project = pr;
            }
        }

        if (project != null) {
            // Ajout de tâches au projet existant
            System.out.println("Entrez le nombre de tâches à ajouter :");
            int numberOfTasks = scanner.nextInt();

            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("Entrez le nom de tâches à ajouter :");
                String taskNameName = scanner.next();
                Task task = new Task(taskNameName, null, new Date(), new Date(), project) {
                    @Override
                    public void performTask() {
                        System.out.println("Effectuer une tâche : " + getTaskName());
                        setTaskStatus(TaskStates.IN_PROGRESS);
                    }
                };


                // Ajouter la tâche à la liste de tâches spécifiée
                tasks.add(task);

                // Ajouter la tâche au projet spécifié
                taskManager.addTaskToProject(task, project);
                System.out.println("Tâche"+taskNameName+ "ajoutée au projet"+projectName+ "avec succès.");
           //     addTaskToProject(taskManager, tasks,project,studentss, scanner);
            }


        } else {
            System.out.println("Projet non trouvé.");
        }
    }



    private static void createProjectAndTask(TaskManager taskManager,List<Task> tasks , List<Student> studentss,List<Project> projects, Scanner scanner) {

        String choice = getProjectChoice(scanner);
        System.out.println("Projet choice name ****" + choice);
        Project project = new Project(choice);


        //description du projet
        System.out.println("Entrez la description du projet :");
        String projectDescription = scanner.next();
        project.setDescription(projectDescription);

        taskManager.addProject(project);
        projects.add(project);

        System.out.println("Projet créé avec succès.");
        addTaskToProject(taskManager,tasks, project,studentss, scanner);

    }

    private static void addTaskToProject(TaskManager taskManager,List<Task>  taskss, Project project,List<Student> studentss, Scanner scanner) {


        System.out.println("Entrez le nom de la tâche :");
        String taskName = scanner.next();
        System.out.println("Nom de la tâche : " + taskName);

        // Assignez des étudiants à la tâche
        List<Student> assignedStudents = assignStudentsFromList(studentss, scanner);


        // Créer une tâche avec le nom spécifié
        Task task = new Task(taskName, assignedStudents, new Date(), new Date(), project) {
            @Override
            public void performTask() {
                System.out.println("Effectuer une tâche : " + getTaskName());
                setTaskStatus(TaskStates.New);
            }
        };


        // Ajouter la tâche à la liste de tâches spécifiée
        taskss.add(task);

        // Ajouter la tâche au projet spécifié
        taskManager.addTaskToProject(task, project);
        System.out.println("Tâche ajoutée au projet avec succès.");



    }


    private static List<Student> assignStudentsFromList(List<Student> studentList, Scanner scanner) {
        List<Student> assignedStudents = new ArrayList<>();

        System.out.println("Entrez le nombre d'étudiants à assigner :");
        int numberOfStudents = scanner.nextInt();
     //   scanner.nextLine(); // Consommer le caractère de nouvelle ligne1
        Boolean trouve = false;

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Entrez le nom de l'étudiant " + (i + 1) + " :");
            String studentName = scanner.next();
            // Vous devrez trouver l'étudiant correspondant dans votre liste existante
            // et ajouter cet étudiant à la liste des étudiants assignés.
            /** SOLUTION TEST **/
            for (Student st : studentList) {
                if (st.getName().equals(studentName));
                assignedStudents.add(st);
                trouve = true;
            }
            if (!trouve){
                System.out.println("Étudiant non trouvé dans la liste.");

            }
            /** END SOLUTION TEST **/


        }

        return assignedStudents;
    }



    private static void assignStudentsToTask(TaskManager taskManager, List<Task> tasks, List<Student> students, List<Project> projects, Scanner scanner) {

        // Affichez la liste des projets déjà créés
        System.out.println("Voici la liste des projets déjà créés :");
        taskManager.displayAllProjects();

        // Obtenez le nom du projet choisi par l'utilisateur
        System.out.println("Choisissez un projet existant :");
        String projectName = scanner.next();

        // Recherche du projet dans la liste existante
        Project project = null;
        for (Project pr : projects){
            if (pr.getProjectName().equals(projectName)){
                project = pr;
            }
        }

        if (project != null) {
            // Affichez la liste des tâches dans le projet choisi
            System.out.println("Choisissez une tâche existante dans le projet '" + projectName + "' :");
            displayAllTasks(project.getTasks());

            // Obtenez le nom de la tâche choisie par l'utilisateur
            String taskName = scanner.nextLine();

            // Assignez des étudiants à la tâche
            List<Student> assignedStudents = assignStudentsFromList(students, scanner);


            // Créer une tâche avec le nom spécifié
            Task task = new Task(taskName, assignedStudents, new Date(), new Date(), project) {
                @Override
                public void performTask() {
                    System.out.println("Effectuer une tâche : " + getTaskName());
                    setTaskStatus(TaskStates.New);
                }
            };


            // Ajouter la tâche à la liste de tâches spécifiée
            tasks.add(task);

            // Ajouter la tâche au projet spécifié
            taskManager.addTaskToProject(task, project);



        } else {
            System.out.println("Projet non trouvé.");
        }
    }








    private static void modifyTaskProgress(TaskManager taskManager, List<Student> student,List<Task>  tasks, Scanner scanner) {
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
            task.setTaskStatus(TaskStates.IN_PROGRESS);

            System.out.println("Avancement de la tâche modifié avec succès.");
        } else {
            System.out.println("Tâche non trouvée ou vous n'êtes pas assigné à cette tâche.");
        }
    }

    private static void addAnotherStudentToTask(TaskManager taskManager, List<Student> student, Scanner scanner) {
        System.out.println("Entrez le nom de la tâche à laquelle ajouter un autre étudiant :");
        String taskName = scanner.nextLine();

        Task task = taskManager.getTasks().stream()
                .filter(t -> t.getTaskName().equals(taskName))
                .findFirst()
                .orElse(null);

        if (task != null && !student.isEmpty()) {
            System.out.println("Entrez le nom de l'étudiant à ajouter à la tâche :");
            String newStudentName = scanner.nextLine();

            // Recherche de l'étudiant dans la liste existante
            Student newStudent = student.stream()
                    .filter(Student -> Student.getName().equals(newStudentName))
                    .findFirst()
                    .orElse(null);

            if (newStudent != null) {
                List<Student> assignedStudents = new ArrayList<>(task.getAssignedStudents());
                assignedStudents.add(newStudent);
                task.setAssignedStudents(assignedStudents);

                System.out.println("Étudiant ajouté à la tâche avec succès.");
            } else {
                System.out.println("Étudiant non trouvé dans la liste.");
            }
        } else {
            System.out.println("Tâche non trouvée ou liste d'étudiants vide.");
        }
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }
    private static String getProjectChoice(Scanner scanner) {
        System.out.print("Saisie Nouvelle Projet : ");
        return scanner.next();
    }
}
