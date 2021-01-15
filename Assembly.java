/**
 * Created by Shimmy on 3/12/2017.
 */
public class Assembly {
    int maxPerAssembly, maxPerMK, maxMKs = 120, negativeId = -1;
    Law[] lawsInAssembly;
    int[] surveyId;
    KnessetMember[] mksInAssembly = new KnessetMember[maxMKs];

    /**
     * Creates a new assembly with the given parameters.
     *
     * @param ​maxLawCapacity The maximal number of laws this assembly can hold.
     * @param ​maxSupportedLawsPerKnessetMember ​The maximal number of laws this assembly allows a single
     *          KnessetMember to support at the same time.
     */
    Assembly(int maxLawCapacity, int maxSupportedLawsPerKnessetMember) {
        maxPerAssembly = maxLawCapacity;
        maxPerMK = maxSupportedLawsPerKnessetMember;
        lawsInAssembly = new Law[maxPerAssembly];
        surveyId = new int[maxPerAssembly];

    }

    /**
     * Adds the given law to this assembly, if there is place available, and it isn't already in the assembly.
     *
     * @param ​law          ​The law to add to this assembly.
     * @param ​surveyResult ​The survey result of the law.
     * @return ​a non-negative id number for the law if there was a spot and the law was successfully
     * added, or if the law was already in the assembly; a negative number otherwise.
     */
    int addLawToAssembly(Law law, int surveyResult) {
        int idIsIn = negativeId;
        for (int i = 0; i < maxPerAssembly; i++) {
            if (lawsInAssembly[i] == law) {
                idIsIn = i;
                surveyId[i] = surveyResult;
                i = maxPerAssembly;
            } else if (lawsInAssembly[i] == null) {
                lawsInAssembly[i] = law;
                idIsIn = i;
                surveyId[i] = surveyResult;
                i = maxPerAssembly;
            }
        }
        return idIsIn;
    }

    /**
     * updates the survey result of lawId with a new value
     *
     * @param ​law            ​law to be updated
     * @param ​newSurveyValue ​new survey value.
     */
    void updateSurveyResultOfLaw(Law law, int newSurveyValue) {
        for (int i = 0; i < maxPerAssembly; i++) {
            if (lawsInAssembly[i] == law) {
                surveyId[i] = newSurveyValue;
                i = maxPerAssembly;
            }
        }
    }

    /**
     * Returns true if the given number is an id of some law in the assembly, false otherwise.
     *
     * @param ​lawId ​The id to check.
     * @return ​true if the given number is an id of some law in the assembly, false otherwise.
     */
    boolean isLawIDValid(int lawId) {
        if (lawId >= 0 && lawId < maxPerAssembly) {
            if (lawsInAssembly[lawId] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the non-negative id number of the given law if he is discussed by this assembly, -1 otherwise.
     *
     * @param ​law ​The law for which to find the id number.
     * @return ​a non-negative id number of the given law if he is discussed by this assembly, -1 otherwise.
     */
    int getLawId(Law law) {
        for (int i = 0; i < maxPerAssembly; i++) {
            if (lawsInAssembly[i] == law) {
                return i;
            }
        }
        return negativeId;
    }

    /**
     * Registers the given KnessetMember to this assembly, if there is a spot available.
     *
     * @param ​KnessetMember ​The KnessetMember to register to this assembly.
     * @return ​a non-negative id number for the KnessetMember if there was a spot and the patron was successfully
     * CS. 67125
     * registered, a negative number otherwise.
     */
    int registerKnessetMember(KnessetMember KnessetMember) {
        for (int i = 0; i < maxMKs; i++) {
            if (mksInAssembly[i] == null) {
                mksInAssembly[i] = KnessetMember;
                return i;
            }
        }
        return negativeId;
    }

    /**
     * Returns true if the given number is an id of a KnessetMember in the assembly, false otherwise.
     *
     * @param ​KnessetMemberId ​The id to check.
     * @return ​true if the given number is an id of a KnessetMember in the assembly, false otherwise.
     */
    boolean isKnessetMemberIdValid(int KnessetMemberId) {
        if (KnessetMemberId >= 0 && KnessetMemberId < maxMKs) {
            if (mksInAssembly[KnessetMemberId] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the non-negative id number of the given KnessetMember if he is registered to this assembly, -1 otherwise.
     *
     * @param ​KnessetMember ​The KnessetMember for which to find the id number.
     * @return ​a non-negative id number of the given KnessetMember if he is registered to this assembly, -1 otherwise.
     */
    int getKnessetMemberId(KnessetMember KnessetMember) {
        for (int i = 0; i < maxMKs; i++) {
            if (mksInAssembly[i] == KnessetMember) {
                return i;
            }
        }
        return negativeId;
    }

    /**
     * adds KnessetMember to law, if the KnessetMember will support the law according to the survey results.
     *
     * @param ​lawId           ​The id number of the law to borrow.
     * @param ​KnessetMemberId ​The id number of the KnessetMember that will support the law.
     * @param ​surveyResult    ​The survey result of the law to support.
     * @return ​true if the KnessetMember was added successfully, false otherwise.
     */
    boolean supportLaw(int lawId, int KnessetMemberId, int surveyResult) {
        if (isLawIDValid(lawId) && isKnessetMemberIdValid(KnessetMemberId)) {
            if (mksInAssembly[KnessetMemberId].willJoinLaw(lawsInAssembly[lawId], surveyResult)) {
                lawsInAssembly[lawId].addJoinedKnessetMember();
                return true;
            }
        }
        return false;
    }

    /**
     * Suggest to the KnessetMember with the given id a law which suits him the best (maximum score of the laws in the
     * assembly).
     *
     * @param ​KnessetMemberId ​The id number of the KnessetMember to suggest the law to.
     * @return ​The best law to match the KnessetMember preferences. Null if there aren't any (case all laws get a zero
     * score).
     * available.
     */
    Law suggestLawToKnessetMember(int KnessetMemberId) {
        double maxLawScore = 0, tempLawScore;
        int bestLawId = negativeId;
        if (isKnessetMemberIdValid(KnessetMemberId)) {
            for (int i = 0; i < maxPerAssembly; i++) {
                tempLawScore = mksInAssembly[KnessetMemberId].getLawScore(lawsInAssembly[i], surveyId[i]);
                if (tempLawScore > maxLawScore) {
                    maxLawScore = tempLawScore;
                    bestLawId = i;
                }
            }
            if (maxLawScore != 0) {
                return lawsInAssembly[bestLawId];
            }
        }
        return null;
    }

}
