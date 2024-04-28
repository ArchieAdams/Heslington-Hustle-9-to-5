const { context: githubContext, github: githubVar } = require('@actions/github');

const commitSha = githubContext.sha;
const testStatus = process.env['GITHUB_JOB'] === 'Test Movement' ? 'All tests passed.' : 'There is something wrong with test codes.';
const state = process.env['GITHUB_JOB'] === 'Test Movement' ? 'success' : 'failure';

githubVar.repos.createCommitStatus({
    ...githubContext.repo,
    sha: commitSha,
    state: state,
    context: 'Automated Code Review',
    description: testStatus
});