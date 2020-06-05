package btp400.ass2;

public class Search {
	/**
	 * checks the data if the provided string is the value for any of the columns
	 * @param x string that is being searched for
	 * @return number of times the string appeared in the data
	 */
	public static int search(String x) {
		int count = 0;
		Offender[] offenders = Offender.readArrayCSV();
		for (int i=0; i < offenders.length; i++) {
			try {
				if (offenders[i].getNumber() == Integer.parseInt(x)) {
					count++;
				}
				if (offenders[i].getRace().equals(x)) {
					count++;
				}
				if (offenders[i].getRaceGrouping().equals(x)) {
					count++;
				}
				if (offenders[i].getGender().equals(x)) {
					count++;
				}
				 
				if (offenders[i].getAge() == Integer.parseInt(x)) {
					count++;
				}
				if (offenders[i].getIncarcerated().equals(x)) {
					count++;
				}
				if (offenders[i].getSupervisorType().equals(x)) {
					count++;
				}
				if (offenders[i].getSentenceType().equals(x)) {
					count++;
				}
				if (offenders[i].getAggSentenceLength() == Integer.parseInt(x)) {
					count++;
				}
				if (offenders[i].getProvince().equals(x)) {
					count++;
				}
				if (offenders[i].getMaritalStatus().equals(x)) {
					count++;
				}
				if (offenders[i].getReligion().equals(x)) {
					count++;
				}
			} catch(Exception e) {
				if (offenders[i].getRace().equals(x)) {
					count++;
				}
				if (offenders[i].getRaceGrouping().equals(x)) {
					count++;
				}
				if (offenders[i].getGender().equals(x)) {
					count++;
				}
				try {
					if (offenders[i].getAge() == Integer.parseInt(x)) {
						count++;
					}
					if (offenders[i].getIncarcerated().equals(x)) {
						count++;
					}
					if (offenders[i].getSupervisorType().equals(x)) {
						count++;
					}
					if (offenders[i].getSentenceType().equals(x)) {
						count++;
					}
					if (offenders[i].getAggSentenceLength() == Integer.parseInt(x)) {
						count++;
					}
					if (offenders[i].getProvince().equals(x)) {
						count++;
					}
					if (offenders[i].getMaritalStatus().equals(x)) {
						count++;
					}
					if (offenders[i].getReligion().equals(x)) {
						count++;
					}
					
				} catch (Exception f) {
					if (offenders[i].getSupervisorType().equals(x)) {
						count++;
					}
					if (offenders[i].getSentenceType().equals(x)) {
						count++;
					}
					try {
						
						if (offenders[i].getAggSentenceLength() == Integer.parseInt(x)) {
							count++;
						}
						if (offenders[i].getProvince().equals(x)) {
							count++;
						}
						if (offenders[i].getMaritalStatus().equals(x)) {
							count++;
						}
						if (offenders[i].getReligion().equals(x)) {
							count++;
						}
					}catch(Exception l) {
						if (offenders[i].getProvince().equals(x)) {
							count++;
						}
						if (offenders[i].getMaritalStatus().equals(x)) {
							count++;
						}
						if (offenders[i].getReligion().equals(x)) {
							count++;
						}
					}
				}
			}
		}
		
		
		return count;
		
}
}
