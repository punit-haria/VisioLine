package testsAndExperiments;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameGenerator;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

public class JGitTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public static void main(String[] args) throws IOException, GitAPIException {
		String localPath, remotePath;
		Repository remoteRepo, localRepo;

		localPath = "C:/Users/Justin/git/android/.git";
		remotePath = "git@github.com:owncloud/android.git";
		remoteRepo = new FileRepository(remotePath);
		localRepo = new FileRepository(localPath);

		BlameCommand blamer = new BlameCommand(localRepo);
		String filePath = "src/com/owncloud/android/authentication/AccountAuthenticator.java";
		blamer.setFilePath(filePath);

		RevWalk revWalk = new RevWalk(localRepo);
		RevCommit root = revWalk.parseCommit(localRepo.resolve("HEAD"));
		revWalk.markStart(root);
		revWalk.setTreeFilter(AndTreeFilter.create(PathFilter.create(filePath),
				TreeFilter.ANY_DIFF));

		// prints out relevant commits (changed file commits)
		for (RevCommit revCommit : revWalk) {
			System.out.println(revCommit.toObjectId().getName());
		}

		// prints out information about file commit
		// blamer.setStartCommit(remoteRepo.resolve("68d4c74fdaba1dcbf135b4b84adfd7def15f2730"));
		BlameResult result = blamer.call();
		RawText text = result.getResultContents();
		for (int i = 0; i < text.size(); ++i) {
			// System.out.print(text.getString(i) + "\n");
			// if(result.getSourceCommit(i).getName().equals(result.getSourceCommitter(i).getName()))
			// {
			// System.out.print(result.getSourceAuthor(i).getName() + "\t");
			System.out.print(i + " "
					+ result.getSourceCommit(i).getCommitTime() + "\t");
			System.out.println(result.getSourceCommitter(i).getName());
			// }
		}

	}

	int getChangesCount(int lineNumber, FileRepository repo)
			throws MissingObjectException, IncorrectObjectTypeException,
			IOException {

		RevWalk revWalk = new RevWalk(repo);
		RevCommit root = revWalk.parseCommit(repo.resolve("HEAD"));
		revWalk.markStart(root);
		revWalk.setTreeFilter(PathFilter
				.create("src/com/owncloud/android/authentication/AccountAuthenticator.java"));

		for (RevCommit revCommit : revWalk) {
			System.out.println(revCommit.toObjectId().getName());
		}
		return lineNumber;
	}
}
