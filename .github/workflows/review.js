const { context, github } = require('@actions/github');
const commitSha = context.sha;
const testStatus = process.env['GITHUB_JOB'] === 'Test Movement' ? 'All tests passed.' : 'There is something wrong with test codes.';
const state = process.env['GITHUB_JOB'] === 'Test Movement' ? 'success' : 'failure';

github.repos.createCommitStatus({
    ...context.repo,
    sha: commitSha,
    state: state,
    context: 'Automated Code Review',
    description: testStatus
});
