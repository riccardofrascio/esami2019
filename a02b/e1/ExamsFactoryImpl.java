package a02b.e1;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static a02b.e1.ExamsFactory.SimpleExamActivities.*;
import static a02b.e1.ExamsFactory.OOPExamActivities.*;

public class ExamsFactoryImpl implements ExamsFactory{
    
    //caso base in cui passo la mappa con chiave l'attività da svolgere 
    //e valore l'attivita che necessità essere svolta prima di quella
    
    private <A> CourseExam<A> fromGraph(Map<A,Set<A>> map) {
        return new CourseExam<A>() {

            private Set<A> done = new HashSet<>();

            @Override
            public Set<A> activities() {
                return map.keySet();
            }

            @Override
            public Set<A> getPendingActivities() {
                return examOver() ? Set.of() : activities().stream().filter(a-> canBeDone(a)).collect(Collectors.toSet());
            }
            
            private boolean canBeDone(A a) {
                return !done.contains(a) && this.done.containsAll(map.get(a));
            }

            @Override
            public void completed(A a) {
                done.add(a);                
            }

            @Override
            public boolean examOver() {
                return done.equals(map.keySet());
            }
            
        };
    }

    
    @Override
    public CourseExam<SimpleExamActivities> simpleExam() {
        return fromGraph(Map.of(
            WRITTEN, Set.of(),
            ORAL, Set.of(WRITTEN),
            REGISTER, Set.of(ORAL)  
        ));
    }

    @Override
    public CourseExam<OOPExamActivities> simpleOopExam() {
        return fromGraph(Map.of(
            LAB_REGISTER, Set.of(),
            LAB_EXAM, Set.of(LAB_REGISTER),
            PROJ_PROPOSE, Set.of(),
			PROJ_SUBMIT, Set.of(PROJ_PROPOSE),
			PROJ_EXAM, Set.of(PROJ_SUBMIT),
			FINAL_EVALUATION, Set.of(PROJ_EXAM,LAB_EXAM)
        ));
    }

    @Override
    public CourseExam<OOPExamActivities> fullOopExam() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
