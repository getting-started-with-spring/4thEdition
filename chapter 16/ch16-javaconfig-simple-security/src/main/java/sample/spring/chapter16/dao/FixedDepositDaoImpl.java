package sample.spring.chapter16.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import sample.spring.chapter16.domain.FixedDepositDetails;

@Repository
public class FixedDepositDaoImpl implements FixedDepositDao {
	private int counter;
	private List<FixedDepositDetails> fdList;

	public FixedDepositDaoImpl() {
		fdList = new ArrayList<FixedDepositDetails>();
		fdList.add(new FixedDepositDetails(counter++, "cust1", "10000", "24",
				"cust1@somedomain.com"));
		fdList.add(new FixedDepositDetails(counter++, "cust2", "10000", "24",
				"cust2@somedomain.com"));
	}

	@Override
	public List<FixedDepositDetails> getFixedDeposits(String user) {
		List<FixedDepositDetails> fds = new ArrayList<FixedDepositDetails>();

		for (FixedDepositDetails details : fdList) {
			if (details.getCustomerId().equalsIgnoreCase(user))
				fds.add(details);
		}
		return fds;
	}

	@Override
	public List<FixedDepositDetails> getAllFixedDeposits() {
		List<FixedDepositDetails> fds = new ArrayList<FixedDepositDetails>();

		for (FixedDepositDetails details : fdList) {
			fds.add(details);
		}
		return fds;
	}

	@Override
	public void saveFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		fixedDepositDetails.setId(counter++);
		fdList.add(fixedDepositDetails);
	}

	public void closeFixedDeposit(int fixedDepositId) {
		for (FixedDepositDetails fixedDepositDetails : fdList) {
			if (fixedDepositDetails.getId() == fixedDepositId) {
				fdList.remove(fixedDepositDetails);
				break;
			}
		}
	}

	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		FixedDepositDetails matchingFixedDepositDetails = null;
		for (FixedDepositDetails fixedDepositDetails : fdList) {
			if (fixedDepositDetails.getId() == fixedDepositId) {
				matchingFixedDepositDetails = fixedDepositDetails;
				break;
			}
		}
		return matchingFixedDepositDetails;
	}

	public void editFixedDeposit(FixedDepositDetails modifiedFixedDepositDetails) {
		for (FixedDepositDetails fixedDepositDetails : fdList) {
			if (fixedDepositDetails.getId() == modifiedFixedDepositDetails
					.getId()) {
				fdList.remove(fixedDepositDetails);
				break;
			}
		}
		fdList.add(modifiedFixedDepositDetails);
	}
}