package com.ole.rentalstore.business.service.crew_strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;

public class CrewProcessorTest {

	@Test
	public void testCrewMemberWorksAsWriter() {
		CrewMemberDTO crewMember = new CrewMemberDTO();
		crewMember.setDepartment("Writing");
		assertThat(CrewProcessor.crewMemberWorksAs(crewMember, "Writing")).isEqualTo(true);
	}
	
	@Test
	public void testCrewMemberWorksAsDirector() {
		CrewMemberDTO crewMember = new CrewMemberDTO();
		crewMember.setJob("Director");
		assertThat(CrewProcessor.crewMemberWorksAs(crewMember, "Director")).isEqualTo(true);
	}
	
	@Test
	public void testCrewMemberWorksAsNone() {
		CrewMemberDTO crewMember = new CrewMemberDTO();
		crewMember.setJob("Director");
		crewMember.setDepartment("foo");
		assertThat(CrewProcessor.crewMemberWorksAs(crewMember, "Writing")).isEqualTo(false);
	}
	
	@Test
	public void testFilterCrewMembers() {
		CrewMemberDTO member1 = new CrewMemberDTO();
		member1.setDepartment("Writing");
		CrewMemberDTO member2 = new CrewMemberDTO();
		member2.setDepartment("foo");
		CrewMemberDTO member3 = new CrewMemberDTO();
		member3.setJob("Director");
		CrewMemberDTO member4 = new CrewMemberDTO();
		member4.setJob("Director");
		CrewMemberDTO member5 = new CrewMemberDTO();
		member5.setDepartment("bar");
		
		List<CrewMemberDTO> crew = Arrays.asList(member1, member2, member3, member4, member5);
		List<CrewMemberDTO> crewFiltered = CrewProcessor.filterCrewMembers(crew, new String[]{ "Director", "Writing" });
		assertThat(crewFiltered.size()).isEqualTo(3);
	}
}