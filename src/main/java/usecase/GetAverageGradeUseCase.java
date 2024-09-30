package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        for (String member : team.getMembers()) {
            Grade[] userGrades = gradeDataBase.getGrades(member);
            for (Grade userGrade : userGrades) {
                if (userGrade.getCourse().equals(course)) {
                    sum += userGrade.getGrade();
                    count++;
                }
            }
        }
        // for every team member, call getGrades for all users
        // check if the grade is for course - if yes, sums += grade, count += 1

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
